// What shows on the web
<template>
    <div id="login">
        <center><h1>ReportIt</h1></center>
        <center><h1>Login</h1></center>
        <input type="text" name="username" v-model="input.username" placeholder="Username" />
        <input type="password" name="password" v-model="input.password" placeholder="Password" />
        <button type="button" v-on:click="login()">Login</button>

    </div>




</template>


// functions
<script type = "text/javascript">
    import axios from 'axios'

    export default {
        name: 'Login',
        data() {
            return {

                input: {
                    username: "",
                    password: ""
                }
            }
        },
        methods: {

            // This will be checking the credentials of the user and if they are right it will continue to the secure (main) page
            login() {


                if(this.input.username != "" && this.input.password != "") {
                  var i;
                  for (i=0;i<this.$parent.users.length; i++){

                    if(this.input.username == this.$parent.users[i].username && this.input.password == this.$parent.users[i].password) {
                        this.$emit("authenticated", true);
                        this.$router.replace({ name: "secure" });


                    } else {
                        console.log("The username and / or password is incorrect");
                    }
                  }
                } else {
                    console.log("A username and password must be present");
                }
            },

            loadcredentials: function () {
                axios.get(`/api/users`)
                    .then(response => {
                        this.users = response.data
                    })
                    .catch(e => {
                        alert(e);
                    })
            }


        }
    }
</script>


// Styling of the web components
//{
//    width: 500px;
//    border: 1px solid #CCCCCC;
//    background-color: #FFFFFF;
//    margin: auto;
//    margin-top: 200px;
//    padding: 20px;
//}

<style scoped>
    #login{
    height: 200px;
    width: 400px;
    border: 1px solid #CCCCCC;
    background-color: #FFFFFF;

    position: fixed;
    top: 50%;
    left: 50%;
    margin-top: -200px;
    margin-left: -200px;
    padding: 20px;
    }
</style>
