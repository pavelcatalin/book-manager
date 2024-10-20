<script setup lang="ts">
import { ref } from 'vue'
import 'ag-grid-community/styles/ag-grid.css' // Mandatory CSS required by the Data Grid
import 'ag-grid-community/styles/ag-theme-quartz.css'
import { AgGridVue } from 'ag-grid-vue3'

const rowData = ref([
  { make: 'Tesla', model: 'Model Y', price: 64950, electric: true },
  { make: 'Ford', model: 'F-Series', price: 33850, electric: false },
  { make: 'Toyota', model: 'Corolla', price: 29600, electric: false }
])

// Column Definitions: Defines the columns to be displayed.
const colDefs = ref([
  { field: 'make', valueGetter: (p) => p.data.make + p.data.price },
  { field: 'model' },
  { field: 'price' },
  { field: 'electric' }
])
</script>
<template>
  <h1>this is the grid view</h1>
  <ag-grid-vue
    :rowData="rowData"
    :columnDefs="colDefs"
    style="height: 500px; width: 90%"
    class="ag-theme-quartz"
  ></ag-grid-vue>
</template>
