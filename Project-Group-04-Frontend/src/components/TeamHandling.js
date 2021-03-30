import axios from 'axios'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort


var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function TechnicianDto(name, isAvailable){
  this.name = name
  this.isAvailable = isAvailable
}

function myFunction() {
    var x = document.getElementById("myTopnav");
    if (x.className === "topnav") {
      x.className += " responsive";
    } else {
      x.className = "topnav";
    }
  }

export default {
    name: 'teamHandling',
    data() {
      return {
        teams: [],
        name: '',
        technicianId: '',
        errorTeam: '',
        reponse: []
      }
    },
    created: function () {
      // Initializing persons from backend
      AXIOS.get('/garageTechnicians')
        .then(response => {
          // JSON responses are automatically parsed.
          this.teams = response.data 
        })
        .catch(e => {
          this.errorProfile = e
        })
        AXIOS.get('/fieldTechnician')
        .then(response => {
          // JSON responses are automatically parsed.
          this.teams += response.data
        })
        .catch(e => {
          this.errorProfile = e
        })
    },
      }
