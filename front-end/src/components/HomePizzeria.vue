<script>
import axios from 'axios';

export default {
      name: 'HomePizzeria',
      data() {
            return {
                  api: 'http://localhost:8080/api/v1/',
                  pizzas: [],
                  search: ""
            }
      },
      created() {

            axios
                  .get(this.api + 'pizza')
                  .then(response => {
                        console.log(response.data);
                        this.pizzas = response.data;
                  })
      },
      methods: {

            input() {
                  axios
                        .get(this.api + 'pizza', {
                              params: {
                                    name: this.search
                              }
                        })
                        .then(response => {
                              console.log(response.data);
                              this.pizzas = response.data;
                        })
            }
      }
}
</script>

<template>
      <div>
            <h1>Pizzeria</h1>

            <input type="text" v-model="search" @keypress="input()">

            <ul>
                  <li v-for="pizza in pizzas">
                        {{ pizza.name }}
                  </li>
            </ul>
      </div>
</template>

<style></style>