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
public class Joins {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    List<String> ids;

    Queue<DeferredResult<String>> results;

    public Joins()
    {
        ids = new ArrayList<String>();
        results = new LinkedList<DeferredResult<String>>();
    }

    public List<String> getIds()
    {
        return ids;
    }

    public void add( String id )
    {
        ids.add( id );
        processDeferredResults();
    }

    public void remove( String id )
    {
        ids.remove( id );
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
            objectMapper.writeValue( sw, ids );
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
