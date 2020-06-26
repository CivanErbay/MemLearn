export const fetchVocabs = async () => {
    const response = await fetch("http://localhost:8080/api/mem")
    return await response.json();
}