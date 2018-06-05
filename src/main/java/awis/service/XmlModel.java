package awis.service;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "sensor")
public class XmlModel {

    public XmlModel(String id, String name) {
    }

    @XmlID
    @XmlElement
    private String id;

    @XmlAttribute
    private String name;

    @XmlElement(name = "sensor-value")
    private Integer value;

    @XmlTransient // don't translate to XML
    private Double computedValue;

}
