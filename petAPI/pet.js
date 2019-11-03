var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var mysql = require('mysql');

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended: true
}));

app.get('/', function (req, res) {
    return res.send({ error: ture, message: 'Test Pet Booking web API' })
});

var dbConn = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'happypets'
});

dbConn.connect();

app.get('/allpetsbooking', function (req,res){
    dbConn.query('SELECT * FROM booking', function (error, results, fields){
        if(error) throw error;
        return res.send(results);
    });
});

app.post('/booking', function (req,res){

    var pet = req.body

    if(!pet) {
        return res.status(400).send({ error:ture, message: 'Please provide booking '});
    }

    dbConn.query("INSERT INTO booking SET ?", pet, function (error, results, fields){
        if (error) throw error;
        return res.send(results);
    });
});

app.post('/register', function (req,res){

    var user = req.body

    if(!user) {
        return res.status(400).send({ error:ture, message: 'Error'});
    }

    dbConn.query("INSERT INTO user SET ?", user, function (error, results, fields){
        if (error) throw error;
        return res.send(results);
    });
});

app.listen(3000, function(){
    console.log('Node app is running on port 3000');
});

module.exports = app;