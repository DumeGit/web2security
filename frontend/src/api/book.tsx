import {BookSearchModel} from "../page/HomePage";

const csrfToken = localStorage.getItem("csrfToken")

export async function search(data:BookSearchModel) {
    const response = await fetch("http://localhost:8080/book/search", { body: JSON.stringify(data), credentials: "include", method: "POST", headers: {
            "Content-Type": "application/json",
            'X-XSRF-TOKEN': csrfToken!
        }})

    if(response.status !== 200) {
        throw new Error("Not logged in");
    }

    return response.json();
}

export async function buy(id:number) {
    const response = await fetch(`http://localhost:8080/book/buy?bookId=${id}`, { method: "GET" , credentials: "include", headers: {
            'X-XSRF-TOKEN': csrfToken!
        }})

    if(response.status !== 200) {
        throw new Error("Not logged in");
    }

    return response.json();
}
