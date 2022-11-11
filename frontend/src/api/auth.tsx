import {UserLogin} from "../model/UserLogin";

export async function login(data: UserLogin) {
    const csrfToken = document.cookie.replace(/(?:(?:^|.*;\s*)XSRF-TOKEN\s*\=\s*([^;]*).*$)|^.*$/, '$1');
    const response = await fetch("https://salty-savannah-57820.herokuapp.com/auth/login", { body: JSON.stringify(data), credentials: "include", method: "POST", headers: {
            "Content-Type": "application/json",
            'X-XSRF-TOKEN': csrfToken!
        }})

    if(response.status !== 200) {
        throw new Error("Not logged in");
    }

    return response.json();
}

export async function getCurrentUser(): Promise<any> {
    const response = await fetch("https://salty-savannah-57820.herokuapp.com/auth/user", { credentials: "include" });

    if(response.status !== 200) {
        throw new Error("Not logged in");
    }
    return response.json();
}

export async function logout(): Promise<Response> {
    return fetch("https://salty-savannah-57820.herokuapp.com/auth/logout", {credentials: "include", method: "GET"})
}

export async function getCsrf() {
    let response = await fetch("https://salty-savannah-57820.herokuapp.com/csrf", {credentials: "include"})
    return response.json();
}
