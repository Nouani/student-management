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

app.post('/resultados', async (req, res) => {
    const { ra, codDisciplina, nota, frequencia } = req.body;

    const alunoExists = await execSQLQuery(`select * from Alunos where RA = ${ra}`);
    if (alunoExists.recordset.length === 0) {
        return res.json(404)
    }

    const disciplinaExists = await execSQLQuery(`select * from Disciplinas where cod = ${codDisciplina}`);
    if (disciplinaExists.recordset.length === 0) {
        return res.json(404)
    }

    const matriculaExists = await execSQLQuery(`select * from Matriculas where RA = ${ra} and cod = ${codDisciplina}`);
    if (matriculaExists.recordset.length === 0) {
        return res.json(404)
    }

    const resultadoExists = await execSQLQuery(`select * from Resultados where RA = ${ra} and cod = ${codDisciplina}`);
    if (resultadoExists.recordset.length != 0) {
        return res.json(404)
    }

    await execSQLQuery(`delete from Matriculas where RA = ${ra} and cod = ${codDisciplina}`);

    await execSQLQuery(`insert into Resultados values (${ra}, ${codDisciplina}, ${nota}, ${frequencia})`);

    return res.json(200);
});

app.listen('3333', () => {
    console.log('Started!');
});