// const url = 'http://localhost:8080'
const url = 'https://bgenderator.herokuapp.com'

const fillDb = async (gender) => {

    let genderSign;

    if (gender === 'male') {
        genderSign = 'm'
    } else if (gender === 'female') {
        genderSign = 'f'
    } else {
        console.log('wrong gender')
        return
    }

    const names = await fetch(`${gender}.txt`)
        .then(response => response.text())
        .then(text => text.split('\n'))              // for heroku
        // .then(text => text.split('\r\n'))               // for localhost

    const chunks = divideNameListToChunks(names, 50)

    for (const chunk of chunks) {
        const tokens = []

        chunk.forEach(name => {
            let tokenDto = {
                name: name,
                gender: genderSign
            }
            tokens.push(tokenDto)
        })

        const body = JSON.stringify(tokens)

        await fetch(`${url}/api/tokens/fill`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: body
        })
            .then(response => response.json())
            .then(result => {
                console.log('Success:', result);
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }
}

function divideNameListToChunks(arr, chunkSize) {
    if (chunkSize <= 0) throw "Invalid chunk size";
    var R = [];
    for (var i=0,len=arr.length; i<len; i+=chunkSize)
        R.push(arr.slice(i,i+chunkSize));
    return R;
}



