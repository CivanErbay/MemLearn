export const fetchVocabs = async () => {
    const response = await fetch("/api/mem")
    return await response.json();
}

export async function performLogin(username, password) {
    const response = await fetch("/auth/login", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({username, password})
    });
    await console.log(response)
    return await response.text()
}