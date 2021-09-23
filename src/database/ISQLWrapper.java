package database;

import database.SQLWrapper;

public interface ISQLWrapper {
    //DELETE WHERE UPDATE SET INSERT VALUES SELECT 
    
    /*
    * @param columns
    * @return
    */
    public SQLWrapper select(Object[] columns);
    
    /*
    * @param table
    * @return
    */
    public SQLWrapper delete(String table);
    
    /*
    * @param table
    * @return
    */
    public SQLWrapper from(String table);
    
    /*
    * @param charSequence
    * @return
    */
    public SQLWrapper where(String charSequence);
    
    /*
    * @param table
    * @return
    */
    public SQLWrapper update(String table);
    
    /*
    * @param words
    * @return
    */
    public SQLWrapper set(Object[] words);
    
    public SQLWrapper set(String word);
    
    public SQLWrapper set(String word, String newValue);
    
    /*
    * @param table
    * @return
    */
    public SQLWrapper insert(String table);
    
    /*
    * @param values
    * @return
    */
    public SQLWrapper values(Object[] values);
    
    public String getQuery();
}
