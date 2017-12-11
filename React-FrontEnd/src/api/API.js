const api = process.env.REACT_APP_CONTACTS_API_URL || 'http://localhost:8080';

const headers = {
    'Accept': 'application/json'
};

export const doLogin = (payload) =>
    fetch(`${api}/user/login`, {
        method: 'POST',
        headers: {
            ...headers,
            'Content-Type': 'application/json'
        },
        credentials:'include',
        body: JSON.stringify(payload)
    }).then(res => {
        console.log(res);
        return res.status;
    })
        .catch(error => {
            console.log("This is error");
            return error;
        });


export const dosignup = (payload) =>
    fetch(`${api}/user/add`, {
        method: 'POST',
        //Mode:'cors',
        headers: {
            ...headers,
            'Content-Type': 'application/json'
        },
        credentials:'include',
        body: JSON.stringify(payload)
    }).then(res => {
        return res.status;
    })
        .catch(error => {
            console.log("This is error");
            return error;
        });


export const logout = () =>
    fetch(`${api}/user/logout`, {
        method: 'POST',
        headers: {
            ...headers
        },
        credentials:'include'
    }).then(res => {
        return res.status;
        res.data;
    })
        .catch(error => {
            console.log("This is error");
            return error;
        });
export const uploadFile = (payload) =>
    fetch(`${api}/upload_info/add`, {
        method: 'POST',
        headers: {
            ...headers,

        },
        body: payload,
        credentials:'include',
    }).then(res => {
        //console.log("IN API UPLOAD");
        return res.status;
    }).catch(error => {
        console.log("This is error");
        return error;
    });

export const star = (payload) =>
    fetch(`${api}/upload_info/star`, {
        method: 'POST',
        headers: {
            ...headers,
            'Content-Type': 'application/json'
        },
        credentials:'include',
        body: JSON.stringify(payload)
    }).then(res => {

        return res.status;
    })
        .catch(error => {
            console.log("This is error");
            return error;
        });

export const Unstar = (payload) =>
    fetch(`${api}/upload_info/unstar`, {
        method: 'POST',
        headers: {
            ...headers,
            'Content-Type': 'application/json'
        },
        credentials:'include',
        body: JSON.stringify(payload)
    }).then(res => {

        return res.status;
    })
        .catch(error => {
            console.log("This is error");
            return error;
        });
export const deletefile = (payload) =>
    fetch(`${api}/upload_info/delete`, {
        method: 'POST',
        headers: {
            ...headers,
            'Content-Type': 'application/json'
        },
        credentials:'include',
        body: JSON.stringify(payload)
    }).then(res => {

        return res.status;
    })
        .catch(error => {
            console.log("This is error");
            return error;
        });

export const Share = (payload) =>
    fetch(`${api}/upload_info/share`, {
        method: 'POST',
        headers: {
            ...headers,
            'Content-Type': 'application/json'
        },
        credentials:'include',
        body: JSON.stringify(payload)
    }).then(res => {

        return res.status;
    })
        .catch(error => {
            console.log("This is error");
            return error;
        });

export const Directory = (payload) =>
    fetch(`${api}/upload_info/directory`, {
        method: 'POST',
        headers: {
            ...headers,
            'Content-Type': 'application/json'
        },
        credentials:'include',
        body: JSON.stringify(payload)
    }).then(res => {
console.log(res.status)
        return res.status;
    })
        .catch(error => {
            console.log("This is error");
            return error;
        });

export const showActivity = () =>
    fetch(`${api}/user_activity/showActivity`,{credentials:'include'})
        .then(res => res.json())
        .catch(error => {
            console.log("This is error.");
            return error;
        });
export const doprofile = (payload) =>
    fetch(`${api}/user_profile/profile`, {
        method: 'POST',
        headers: {
            ...headers,
            'Content-Type': 'application/json'
        },
        credentials:'include',
        body: JSON.stringify(payload)
    }).then(res => {
        return res.status;
    })
        .catch(error => {
            console.log("This is error");
            return error;
        });

export const getDetails = () =>
    fetch(`${api}/user_profile/getdetails`,{credentials:'include'})
        .then(res => res.json())
        .catch(error => {
            console.log("This is error.");
            return error;
        });

export const getImages = () =>
    fetch(`${api}/upload_info/getimg`,{credentials:'include'})
        .then(res => res.json())
        .catch(error => {
            console.log("This is error.");
            return error;
        });
