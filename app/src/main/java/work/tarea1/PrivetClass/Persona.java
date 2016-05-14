package work.tarea1.PrivetClass;

/**
 * Created by David-PC on 11/5/2016.
 */
public class Persona {




    /*Se definen el nombre de las columnas
    public static final String CARNET = "CARNET";
    public static final String NOMBRE = "NOMBRE";
    public static final String APELLIDO = "APELLIDO";
    public static final String CORREO= "CORREO";
    public static final String SEXO= "SEXO";*/


        //Campos de la tabla
        private String idPersona;
        private String idTipoPersona;
        //@DatabaseField(columnName=CARNET,id =true ,unique = true,canBeNull = false,index = true)
        //private String carnet;
        //@DatabaseField(columnName = NOMBRE,canBeNull = false)
        private String nombre;
        private String dui;
        private String gradoAcademico;


        //@DatabaseField(columnName = APELLIDO,canBeNull = false)
        private String apellido;
        //@DatabaseField(columnName = CORREO)
        private String email;
        //@DatabaseField(columnName =SEXO,canBeNull = false)
        private String genero;

        public Persona() {
        }

        public Persona(String idPersona, String idTipoPersona, String nombre,String apellido, String dui, String gradoAcademico, String genero , String email) {
            this.idPersona = idPersona;
            this.idTipoPersona = idTipoPersona;
            this.nombre = nombre;
            this.dui = dui;
            this.gradoAcademico = gradoAcademico;

            this.apellido = apellido;
            this.email = email;
            this.genero = genero;
        }

    /*public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }*/

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }





        public String getIdPersona() {
            return idPersona;
        }

        public void setIdPersona(String idPersona) {
            this.idPersona = idPersona;
        }

        public String getIdTipoPersona() {
            return idTipoPersona;
        }

        public void setIdTipoPersona(String idTipoPersona) {
            this.idTipoPersona = idTipoPersona;
        }

        public String getDui() {
            return dui;
        }

        public void setDui(String dui) {
            this.dui = dui;
        }

        public String getGradoAcademico() {
            return gradoAcademico;
        }

        public void setGradoAcademico(String gradoAcademico) {
            this.gradoAcademico = gradoAcademico;
        }




        public void setGenero(String genero) {
            this.genero = genero;
        }

        public String getGenero(){
            return genero;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }


