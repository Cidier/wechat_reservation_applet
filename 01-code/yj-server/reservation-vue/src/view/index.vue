<script setup>

import {ref} from "vue";

const size = ref(20)

</script>

<template>
  <div style="width: 100%;">
    <el-space wrap :size="size">
      <el-calendar />
    </el-space>
  </div>
</template>

<style scoped>

</style>
