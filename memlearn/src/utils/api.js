export const fetchVocabs = async () => {
    const response = await fetch("/api/mem")
    return await response.json();
}