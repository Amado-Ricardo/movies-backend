package data;

public class DaoFactory {

    private static MoviesDao moviesDao;
    private static data.config.Config config = new config.Config();
    public enum ImplType {MYSQL, IN_MEMORY};

    public static MoviesDao getMoviesDao(ImplType implementationType){

        switch(implementationType){
            case IN_MEMORY:{
                if (moviesDao == null){
                    moviesDao = new InMemoryMoviesDao();
                }
            }
            case MYSQL:{
                if (moviesDao == null){
                    moviesDao = new MySqlMoviesDao(config);
                }
            }
        }
        return moviesDao;
    }
}