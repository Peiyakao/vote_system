<script>

</script>

<template>
  <nav class="nav">
    <router-link to="/">投票網站(前台)</router-link> |
    <router-link to="/back">投票網站(後台)</router-link>
  </nav>
</template>

<style scoped>
  nav.nav {
    text-align: center;
    font-size: 1.2rem;
    color: #999;
    margin: 10px 0;
  }
  nav.nav a {
    color: #999;
    display: inline-block;
    
    text-decoration: none;

    padding: 0.2rem 0.5rem;
  }
  nav.nav a:hover {
    color: #333;
  }
  nav.nav .router-link-exact-active {
    color: #333;
  }
  nav.nav a:hover, nav.nav .router-link-exact-active {
    background-color: #eee;
    border-radius: 0.5rem;
  }
</style>