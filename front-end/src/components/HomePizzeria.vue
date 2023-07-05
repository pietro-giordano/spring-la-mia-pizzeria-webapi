<script>
import axios from 'axios';

export default {
      name: 'HomePizzeria',
      data() {
            return {
                  api: 'http://localhost:8080/api/v1/',
                  pizzas: [],
                  search: "",
                  newPizza: {
                        name: "",
                        description: "",
                        image: "",
                        price: "",
                  }
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
            },

            createPizza() {
                  axios
                        .post(this.api + 'pizza', {
                              name: this.newPizza.name,
                              description: this.newPizza.description,
                              image: this.newPizza.image,
                              price: this.newPizza.price
                        })
                        .then(response => {
                              console.log(response.data);
                              this.input();
                        })
            },

            deletePizza(index) {
                  axios
                        .delete(this.api + 'pizza/' + this.pizzas[index].id)
                        .then(response => {
                              console.log(response.data);
                              this.input();
                        })
            }
      }
}
</script>

<template>
      <div class="container mx-auto ">
            <h1 class="text-4xl text-red-500">Pizzeria</h1>

            <div class="grid grid-cols-2">
                  <div>
                        <input type="text" v-model="search" @keyup="input()">

                        <ul class="divide-y divide-gray-100 pt-8 pb-20">
                              <li v-for="pizza, index in pizzas"
                                    class="flex justify-between items-center gap-x-6 p-5 text-xl font-semibold leading-6 text-gray-900">
                                    {{ pizza.name }}
                                    <button @click="deletePizza(index)"
                                          class="block w-32 text-start px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">
                                          Delete
                                    </button>
                              </li>
                        </ul>
                  </div>

                  <div>
                        <input v-model="newPizza.name" type="text" name="name" id="name" placeholder="Inserisci nome" required
                              class="block flex-1 border-0 bg-transparent py-1.5 pl-1 text-gray-900 placeholder:text-gray-400 focus:ring-0 sm:text-sm sm:leading-6">

                        <textarea v-model="newPizza.description" name="description" id="description" cols="30" rows="10"
                              placeholder="Inserisci descrizione"
                              class="block flex-1 border-0 bg-transparent py-1.5 pl-1 text-gray-900 placeholder:text-gray-400 focus:ring-0 sm:text-sm sm:leading-6"></textarea>

                        <input v-model="newPizza.image" type="text" name="image" id="image"
                              placeholder="Inserisci url immagine"
                              class="block flex-1 border-0 bg-transparent py-1.5 pl-1 text-gray-900 placeholder:text-gray-400 focus:ring-0 sm:text-sm sm:leading-6">

                        <input v-model="newPizza.price" type="number" name="price" id="price" step="0.01"
                              pattern="\d+(\.\d{1,2})?" placeholder="Inserisci prezzo"
                              class="block flex-1 border-0 bg-transparent py-1.5 pl-1 text-gray-900 placeholder:text-gray-400 focus:ring-0 sm:text-sm sm:leading-6">

                        <button @click="createPizza()"
                              class="block w-32 text-start px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">Crea
                              pizza</button>
                  </div>
            </div>
      </div>
</template>

<style></style>