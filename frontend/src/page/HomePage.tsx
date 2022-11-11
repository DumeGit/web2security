import {Badge, Button, Card, Navbar} from "react-bootstrap";
import React, {useEffect, useState} from "react";
import {BookModel} from "../model/BookModel";
import {Field, Form, Formik} from "formik";
import * as api from "../api/book";
import {logout} from "../api/auth";
import {getAll, sendMoney} from "../api/user";

export interface BookSearchModel {
    bookName: string,
    sqlInjection: boolean
}

export default function HomePage({user}: any) {
    const [books, setBooks] = useState<BookModel[]>([])
    const [users, setUsers] = useState<any>([]);

    useEffect(() => {
        getAllUsers();
    }, [])

    async function getAllUsers() {
        let response = await getAll();
        setUsers(response);
    }

    const onSearch = async (values: BookSearchModel) => {
        let response = await api.search(values);
        setBooks(response);
    };

    async function onBuy(id: number) {
        api.buy(id);
    }

    function onSendMoney(values: any) {
        sendMoney(values);
    }

    function onLogout() {
        logout()
        window.location.reload();
    }

    return (
        <div>
            <Navbar
                color="faded"
            >
                <Navbar.Brand
                    className="me-auto"
                    href="/"
                >
                    <Badge className="mx-3">
                        {user.email}
                    </Badge>
                    <Badge>
                        {user.money + "$"}
                    </Badge>
                </Navbar.Brand>
                <Navbar.Text>
                    <button
                        onClick={() => onLogout()}
                        className=" w-5 group relative mx-3 w-full flex justify-center py-2 px-10 border border-transparent text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                        Logout
                    </button>
                </Navbar.Text>

            </Navbar>

            <Card className="p-10">
                <Card.Header>
                    <div className="justify-content-between flex flex-row">
                        <div className="w-50">
                            <Formik initialValues={{bookName: "", sqlInjection: false}} onSubmit={onSearch}>
                                {() => {
                                    return (
                                        <Form className={"w-25"}>
                                            <div className="text-left ">
                                                <label>Book name</label>
                                                <Field name="bookName" type="text" className="form-control"/>
                                            </div>
                                            <div>
                                                <label>Sql injection</label>
                                                <Field name="sqlInjection"
                                                       type="checkbox"
                                                />
                                            </div>
                                            <div className="flex flex-row justify-content-start pr-3 mt-3 w-full">
                                                <button
                                                    className=" w-5 group relative w-full flex justify-center py-2 px-10 border border-transparent text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                                                    type="submit">
                                                    Search
                                                </button>
                                            </div>
                                        </Form>
                                    );
                                }}
                            </Formik>
                        </div>
                        <div>
                            <a href="http://localhost:4000">
                                <button
                                    className=" w-5 group relative flex justify-center py-2 px-10 border border-transparent text-sm font-medium rounded-md text-white bg-red-600 hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-red-500"
                                >
                                    Click for csrf attack example
                                </button>
                            </a>
                        </div>
                        {users.length && <div className="w-50 justify-content-end flex">
                            <Formik initialValues={{email: users[0].email, money: 0}} onSubmit={onSendMoney}>
                                {() => {
                                    return (
                                        <Form className={"w-25"}>
                                            <div className="text-left ">
                                                <label>User</label>
                                                <Field name="email" defaultValue={users.length && users[0].email} as="select" className="form-control">
                                                    {
                                                        users.map((user: any) =>
                                                            <option value={user.email}>
                                                                {user.email}
                                                            </option>
                                                        )
                                                    }
                                                </Field>

                                                <label>Money</label>
                                                <Field name="money" type="number" className="form-control">
                                                </Field>
                                            </div>
                                            <div className="flex flex-row justify-content-start pr-3 mt-3 w-full">
                                                <button
                                                    className=" w-5 group relative w-full flex justify-center py-2 px-10 border border-transparent text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                                                    type="submit">
                                                    Send money
                                                </button>
                                            </div>
                                        </Form>
                                    );
                                }}
                            </Formik>
                        </div>}

                    </div>
                </Card.Header>
                <Card.Body>
                    <div className="flex flex-col">
                        <div className="-my-2 overflow-x-auto sm:-mx-6 lg:-mx-8">
                            <div className="py-2 align-middle inline-block min-w-full sm:px-6 lg:px-8">
                                <div className="shadow overflow-hidden border-b border-gray-200 sm:rounded-lg">
                                    <table className="min-w-full divide-y divide-gray-200">
                                        <thead className="bg-gray-50">
                                        <tr>
                                            <th
                                                scope="col"
                                                className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
                                            >
                                                Name
                                            </th>
                                            <th
                                                scope="col"
                                                className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
                                            >
                                                Genre
                                            </th>
                                            <th
                                                scope="col"
                                                className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
                                            >
                                                Price
                                            </th>
                                            <th
                                                scope="col"
                                                className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
                                            >
                                                Seller
                                            </th>
                                            <th
                                                scope="col"
                                                className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
                                            >
                                                Buy
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody className="bg-white divide-y divide-gray-200">
                                        {
                                            books.map((book) => (
                                                <tr key={book.id}>
                                                    <td className="px-6 py-4 whitespace-nowrap">
                                                        <div className="flex items-center">
                                                            <div className="ml-4">
                                                                <div
                                                                    className="text-sm font-medium text-gray-900">{book.name}</div>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td className="px-6 py-4 whitespace-nowrap">
                                                        <div className="flex items-center">
                                                            <div className="ml-4">
                                                                <div
                                                                    className="text-sm font-medium text-gray-900">{book.genre}</div>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td className="px-6 py-4 whitespace-nowrap">
                                                        <div
                                                            className="text-sm text-gray-900">{book.price}</div>
                                                    </td>
                                                    <td className="px-6 py-4 whitespace-nowrap">
                                                        <div className="flex items-center">
                                                            <div className="ml-4">
                                                                <div
                                                                    className="text-sm font-medium text-gray-900">{book.seller}</div>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                                                        <Button onClick={() => onBuy(book.id)}>Buy</Button></td>
                                                </tr>
                                            ))}
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </Card.Body>
            </Card>
        </div>
    )
}
