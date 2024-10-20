interface Book {
  name: string
  author: string
}

const getBaseURL = () => {
  return 'http://localhost:8080/books'
}

export const addBook = async (body: Book) => {
  const response = await fetch(getBaseURL(), {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      Accept: 'application/json'
    },
    body: JSON.stringify(body)
  })

  return response.status
}

export const getBooks = async () => {
  const response = await fetch(getBaseURL())
  const data = await response.json()

  return data
}
export const getBook = async (id: number) => {
  const response = await fetch(`${getBaseURL()}/${id}`)
  const data = await response.json()

  return data
}
export const deleteBook = async (id: number) => {
  const response = await fetch(`${getBaseURL()}/${id}`, {
    method: 'DELETE'
  })
  return response
}
export const updateBook = async (id: number, body: Book) => {
  const response = await fetch(`${getBaseURL()}/${id}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(body)
  })
  return response
}
