const express = require('express');
const bodyParser = require('body-parser');
const mssql = require('mssql');

mssql.connect("Server=regulus.cotuca.unicamp.br;Database=BD19174;User Id=BD19174;Password=guilhermepoder;")
    .then(conn => global.conn = conn)
    .catch(err => console.log(err));

function execSQLQuery(sqlQry) {
    return global.conn.request().query(sqlQry);
}

const app = express();

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

app.listen('3333', () => {
    console.log('Started!');
});