package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import beans.*;
 
public class HibernateUtil
{
    private static final SessionFactory sessionFactory;
    static
    {
        try
        {
        	 AnnotationConfiguration config = new AnnotationConfiguration();
             config.addAnnotatedClass(TemaBean.class);
             config.addAnnotatedClass(LeccionBean.class);
             config.addAnnotatedClass(PersonaBean.class);
             config.addAnnotatedClass(AlumnoBean.class);
             config.addAnnotatedClass(DocenteBean.class);
             config.addAnnotatedClass(AvatarBean.class);
             config.addAnnotatedClass(EnsenianzaBean.class);
             config.addAnnotatedClass(CursoBean.class);
             config.addAnnotatedClass(JuegoBean.class);
             config.addAnnotatedClass(LietnerBean.class);
             config.addAnnotatedClass(ValorLietnerBean.class);
             config.addAnnotatedClass(AlimentoBean.class);
             config.addAnnotatedClass(TipoAvatarBean.class);
             config.addAnnotatedClass(HistorialAlumnoBean.class);
             
             sessionFactory = config.buildSessionFactory(); 
        }
        catch (Throwable ex)
        {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}
