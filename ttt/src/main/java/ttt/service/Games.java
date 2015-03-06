package ttt.service;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Games {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    List<Boolean> values;

    Queue<DeferredResult<String>> results;

    public Games()
    {
    	values = new ArrayList<Boolean>();
        results = new LinkedList<DeferredResult<String>>();
    }

    public List<Boolean> getValues()
    {
        return values;
    }

    public void add( Boolean id )
    {
    	values.add( id );
        processDeferredResults();
    }

    public void remove( Boolean id )
    {
    	values.remove( id );
        processDeferredResults();
    }
    
    public void change( int i , Boolean id )
    {
    	values.set( i,id );
        processDeferredResults();
    }

    public void addResult( DeferredResult<String> result )
    {
        results.add( result );
    }

    private void processDeferredResults()
    {
        String json = "";
        try
        {
            StringWriter sw = new StringWriter();
            objectMapper.writeValue( sw, values );
            json = sw.toString();
        }
        catch( Exception e )
        {
        }

        while( !results.isEmpty() )
        {
            DeferredResult<String> result = results.remove();
            result.setResult( json );
        }
    }

}
