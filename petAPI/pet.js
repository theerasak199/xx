var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var mysql = require('mysql');
var user_id

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

app.get('/profile', function (req,res){
    dbConn.query('SELECT * FROM user', function (error, results, fields){
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

app.post('/login', function(req,res){
    var data = req.body;
    var username = data.username;
    var password = data.password;

    dbConn.query('SELECT * FROM user WHERE username = ? AND password =?', [username, password], function(error, results, fields) { 
        dbConn.on('error', function(err) {
            console.log('[MySQL ERROR]',err) 
        });
        if (results && results.length) {
            res.end(JSON.stringify(results[0]));
        }
        else {
            res.end(JSON.stringify('Wrong password'));
        }
    });
});



app.put('/petBooking_id/:id', function(req, res){
    let booking_id = req.params.id;
    let pet = req.body;

    if(!booking_id || !pet){
        return res.status(400).send({error:user, message:'Please provide pet data and booking_id'});
    }
    dbConn.query("UPDATE booking SET ? WHERE booking_id =?",[pet, booking_id],function(error,results,fields){
        if (error) throw error;
        return res.send({error: false,data: results, message:'Booking has been update successfully.'});
    });
});

app.delete('/petBooking_id/:id',function(req,res){
    let petBooking_id = req.params.id;
    if(!petBooking_id){
        return res.status(400).send({error : true, message:'Please provide petBooking_id'});
    }
    dbConn.query('DELETE FROM booking WHERE booking_id = ?',petBooking_id,function(error ,results,fields){
        if (error) throw error;
        return res.send({error: false,data: results,message:'Booking has been delete successfully.'});
    });
});

app.listen(3000, function(){
    console.log('Node app is running on port 3000');
});

module.exports = app;