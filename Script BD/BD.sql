create table temas (
temaId int primary key,
descripcion varchar(50)
)

create table juegos (
juegoId int primary key,
nombre varchar(50),
temaId int foreign key references temas(temaId)
)

create table avatares (
avatarId int primary key,
)

create table alumnos(
alumnoId int primary key,
tipoDocumento varchar(50),
nroDocumento int,
nombre varchar(50),
apellido varchar(50),
puntos int,
avatarId int foreign key references avatares(avatarId)
)


create table docentes(
docenteId int primary key,
tipoDocumento varchar(50),
nroDocumento int,
nombre varchar(50),
apellido varchar(50),
)

create table cursos(
cursoId int primary key,
descripcion varchar(50),
docenteID int foreign key references docentes(docenteId)
)


create table elementosAvatar (
elementoAvatarId int primary key,
descripcion varchar(50),
tipo varchar(50),
color varchar(50),
avatarId int foreign key references avatares(avatarId)
)


create table lecciones(
leccionId int primary key,
descripcion varchar(50),
temaId int foreign key references temas(temaId),
juegoId int foreign key references juegos(juegoId)
)

create table ensenianzas(
ensenianzaId int primary key,
leccionId int foreign key references lecciones(leccionID),
nivelRefuerzo int,
alumnoId int foreign key references alumnos(alumnoId),
fechaUltimoRepaso datetime
)

create table curso_alumno(
alumnoId int foreign key references alumnos(alumnoId),
cursoID int foreign key references cursos(cursoId)
)