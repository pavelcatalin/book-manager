<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <div class="app-wrapper">
    <!-- <RouterLink to="/gridview">Grid view</RouterLink> -->
    <RouterView />
  </div>
</template>

<style scoped>
.app-wrapper {
  min-height: 100vh;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  background-image: url('https://c1.wallpaperflare.com/preview/917/425/277/library-building-interior-library-interior-book.jpg');
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
}
</style>
