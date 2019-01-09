<template>
    <div id="app">

          <!--<input type='button' v-on:click="loadcredentials()" value='Retrieve data'>
              <ul>
                <li v-for="user in users">
                  {{ user.username }}
                  {{ user.password }}
                </li>
              </ul>
            <br></br>-->

        <div id="nav">
            <router-link v-if="authenticated" to="/login" v-on:click.native="logout()" replace>Logout</router-link>
        </div>

        <router-view @authenticated="setAuthenticated" />

    </div>
</template>

<script type = "text/javascript">
    import axios from 'axios'
    export default {
        name: 'App',
        data() {
            return {
                authenticated: false,
                users: []

            }
        },

        // If the authentication fails go back to login page
        mounted() {

            axios.get(`/api/users`)
                .then(response => {
                    this.users = response.data
                })
                .catch(e => {
                    alert(e);
                })

            if(!this.authenticated) {
                this.$router.replace({ name: "login" });
            }
        },


        methods: {

            // After creating the Java file
            loadcredentials: function () {
                axios.get(`/api/users`)
                    .then(response => {
                        this.users = response.data
                    })
                    .catch(e => {
                        alert(e);
                    })
            },


            setAuthenticated(status) {
                this.authenticated = status;
            },
            logout() {
                this.authenticated = false;
            },



        }


    }
</script>

//{
//    width: 1024px;
//    margin: auto;
//}

<style>
    body {
        background-color: #F0F0F0;
    }
    h1 {
        padding: 0;
        margin-top: 0;
    }
    #app
    {
    height: 200px;
    width: 400px;

    position: fixed;
    top: 50%;
    left: 50%;
    margin-top: -300px;
    margin-left: -200px;

    }
</style>
