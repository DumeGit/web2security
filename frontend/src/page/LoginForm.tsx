import React, {useEffect} from 'react';
import {Card} from "react-bootstrap";
import {Formik, Form, Field} from "formik";
import {UserLogin} from "../model/UserLogin";
import * as api from "../api/auth"
import {getCurrentUser} from "../api/auth";

interface LoginFormProps {
    setUser: any;
}

export function LoginForm ({setUser} : LoginFormProps){

    const onSubmit = async (values: UserLogin) => {
        let response = await api.login(values);
        if(response) {
            localStorage.setItem("currentUser", JSON.stringify(response));
            getCurrentUser().then(item => {
                setUser(item)
            });
        }
    };

    return (
        <div className="p-10 justify-content-center align-items-center flex h-auto w-auto">
            <Card>
                <Card.Body>
                    <Formik initialValues={{email: "", password: ""}} onSubmit={onSubmit}>
                        {() => {
                            return (
                                <Form>
                                    <div className="text-left ">
                                        <label>E-mail</label>
                                        <Field name = "email" type = "email" className="form-control"/>
                                        <label>Password</label>
                                        <Field name = "password" type = "password" className="form-control"/>
                                    </div>
                                    <div className="flex flex-row justify-center pr-3 pl-3 mt-3 w-full">
                                        <button className="group relative w-full flex justify-center py-2 px-10 border border-transparent text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500" type="submit">
                                            Login
                                        </button>
                                    </div>
                                </Form>
                            );
                        }}
                    </Formik>
                </Card.Body>
            </Card>
        </div>
    );
}
