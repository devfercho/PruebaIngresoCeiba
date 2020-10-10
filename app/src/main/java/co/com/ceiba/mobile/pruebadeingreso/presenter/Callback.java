package co.com.ceiba.mobile.pruebadeingreso.presenter;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.dto.User;

/**
 * Clase para obtener el resultado de consultar los usuarios
 * con el AsynkTask PresenteMaster
 */
public class Callback {

    public static final int OK = 0; //Indica que los usuarios ya estaban guardados localmente
    public static final int REST = 1; //Indica que la consulta REST se debe realizar

    private int result;
    private List<User> user;


    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }


}
