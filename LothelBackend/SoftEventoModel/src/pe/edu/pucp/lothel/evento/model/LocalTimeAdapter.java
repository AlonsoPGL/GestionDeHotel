/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.lothel.evento.model;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalTime;

/**
 *
 * @author Melanie
 */
public class LocalTimeAdapter extends XmlAdapter<String, LocalTime>{

    @Override
    public LocalTime unmarshal(String vt) throws Exception {
        LocalTime lt = LocalTime.parse(vt);
                return lt;
    }

    @Override
    public String marshal(LocalTime bt) throws Exception {
        return bt.toString();
    }
    
}
