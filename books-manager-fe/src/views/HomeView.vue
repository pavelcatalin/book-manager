<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { addBook, getBooks, deleteBook, getBook, updateBook } from '../services/books.ts'

interface Book {
  name: string
  author: string
}

const book = ref('')
const author = ref('')
const allBooks: Book[] = ref([])
const editMode = ref(false)
const bookToUpdateId = ref(null)

const fetchBooks = async () => {
  try {
    allBooks.value = await getBooks()
    console.log(allBooks.value)
  } catch (error) {
    console.error('Error fetching books:', error)
  }
}

const submitForm = async () => {
  const formValue = {
    name: book.value,
    author: author.value
  }
  try {
    let response
    if (editMode.value) {
      response = await updateBook(bookToUpdateId.value, formValue)
    } else {
      response = await addBook(formValue)
    }

    if (response === 200 || response.status === 200) {
      fetchBooks()
      book.value = ''
      author.value = ''
      editMode.value = false
    }
  } catch (error) {
    console.log(error)
  }
}

const removeBook = async (id) => {
  const response = await deleteBook(id)
  console.log(response)

  if (response.status === 200) {
    fetchBooks()
  }
}

const fetchBook = async (id) => {
  const response = await getBook(id)
  bookToUpdateId.value = id
  book.value = response.name
  author.value = response.author
  editMode.value = true
}

onMounted(() => {
  fetchBooks()
})
</script>

<template>
  <main>
    <!-- <h1>Books manager</h1> -->

    <form @submit.prevent="submitForm">
      <div class="form-item">
        <label for="book">Add book</label>
        <input type="text" name="book" id="book" v-model="book" />
      </div>
      <div class="form-item">
        <label for="author">Add author</label>
        <input type="text" name="author" id="author" v-model="author" />
      </div>

      <button v-if="!editMode">Add book</button>
      <button v-else>Update book</button>
    </form>

    <div v-for="book in allBooks" v-bind:key="book.id" style="color: white" :id="book.id">
      <div class="book">
        <span @click="removeBook(book.id)">x</span>
        <div>{{ book.name }} by {{ book.author }}</div>
        <button @click="fetchBook(book.id)">Edit</button>
      </div>
    </div>
  </main>
</template>

<style scoped>
h1 {
  color: white;
}

main {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  font-family: Arial, Helvetica, sans-serif;
}

form {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  height: 300px;
  width: 300px;
  padding: 10px;
  background-color: white;
  opacity: 0.8;
  border-radius: 10px;
}

.form-item {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.book {
  display: flex;
  flex-direction: column;
  align-items: end;
  padding: 5px;
  margin: 5px;
  border: 1px solid white;
}

.book span {
  margin: 5px;
  cursor: pointer;
}
.book button {
  margin: 5px;
  padding: 5px;
}
</style>
