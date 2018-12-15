/*
 * Example singleton implementation
 */
class Singleton {
       private static volatile Singleton instance;
       
       private String dummyAttribute;
       
       private Singleton() {
       }
       
       public static Singleton getInstance() {
         // Thread-safe via double-checked locking
      if(instance == null) {
          synchronized(Singleton.class) {
             if(instance == null) {
                instance = new Singleton();
             }
          }
      }
      return instance;
       }
       
       public synchronized String getAttribute() {
              return dummyAttribute;
       }
       
       public synchronized void setAttribute(String attribute) {
              this.dummyAttribute = attribute;
       }
}
       
public class SingletonExample {
       
       public static void main(String[] args) {
              
              Singleton single = Singleton.getInstance();
              
              single.setAttribute("Testing");
              
              System.out.println(single.getAttribute());
       }
}