<script>
  import TheNavigation from "@/components/TheNavigation.vue";

  export default {
    components: {TheNavigation},
    data(){
      return {};
    }
  }
</script>

<template>
  
  <TheNavigation></TheNavigation>
  <router-view v-slot="{Component}">
    <component :is="Component" :key="$route.path"></component>
  </router-view>

</template>
<style scoped>
</style>