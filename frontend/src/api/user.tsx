const csrfToken = localStorage.getItem("csrfToken")

export async function getAll() {
    const response = await fetch("https://salty-savannah-57820.herokuapp.com/user/getAll", {
        credentials: "include", method: "GET", headers: {
            'X-XSRF-TOKEN': csrfToken!
        }
    })

    if (response.status !== 200) {
        throw new Error("Not logged in");
    }

    return response.json();
}

export async function sendMoney(data:any) {
    const response = await fetch(`https://salty-savannah-57820.herokuapp.com/user/send?email=${data.email}&money=${data.money}`, { method: "GET" , credentials: "include", })

    if(response.status !== 200) {
        throw new Error("Not logged in");
    }

    return response.json();
}
