/**
 * This software was developed and / or modified by Raytheon Company,
 * pursuant to Contract DG133W-05-CQ-1067 with the US Government.
 * 
 * U.S. EXPORT CONTROLLED TECHNICAL DATA
 * This software product contains export-restricted data whose
 * export/transfer/disclosure is restricted by U.S. law. Dissemination
 * to non-U.S. persons whether in the United States or abroad requires
 * an export license or other authorization.
 * 
 * Contractor Name:        Raytheon Company
 * Contractor Address:     6825 Pine Street, Suite 340
 *                         Mail Stop B8
 *                         Omaha, NE 68106
 *                         402.291.0100
 * 
 * See the AWIPS II Master Rights File ("Master Rights File.pdf") for
 * further licensing information.
 **/
package gov.noaa.nws.ncep.common.dataplugin.editedregions.util;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.raytheon.uf.common.localization.IPathManager;
import com.raytheon.uf.common.localization.LocalizationContext;
import com.raytheon.uf.common.localization.PathManagerFactory;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.exception.EditedRegionsException;

/**
 * Utility class
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Nov 9, 2015  R9583      sgurung     Initial creation
 * 
 * </pre>
 * 
 * @author sgurung
 * @version 1.0
 */

public class EditedEventsUtil {

    /**
     * Validate the xml file against a schema defined in the input xml file
     * 
     * @param xmlFile
     *            : XML file
     * @return true/false
     */
    public static boolean validateXmlFileAgainstSchema(File xmlFile)
            throws EditedRegionsException {

        // parse an XML document into a DOM tree
        DocumentBuilder parser = null;
        Document document;
        String xsdFileName = null;
        File xsdSchemaFile = null;

        // do the validation, if there are any issues with the
        // validation process the input is considered invalid.
        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);

            // validate the DOM tree
            parser = dbf.newDocumentBuilder();
            document = parser.parse(xmlFile);

            Element root = document.getDocumentElement();

            xsdFileName = root.getAttributeNS(
                    XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI,
                    "noNamespaceSchemaLocation");

            xsdSchemaFile = getXSDFile(xsdFileName);

            if (xsdSchemaFile == null)
                return false;

            // create a SchemaFactory capable of understanding WXS schemas
            SchemaFactory factory = SchemaFactory
                    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            // load a WXS schema, represented by a Schema instance
            Source schemaFile = new StreamSource(xsdSchemaFile);
            Schema schema = factory.newSchema(schemaFile);

            // create a Validator instance, which can be used to validate an
            // instance document
            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(document));

        } catch (Exception e) {
            e.printStackTrace();
            throw new EditedRegionsException("Error validating input xml file '"
                    + xmlFile + "' against schema '"
                    + xsdSchemaFile.getAbsolutePath() + "'");

        }

        return true;
    }

    /**
     * Returns file with given name.
     * 
     * @param xsdFileName
     *            : XSD file name
     * @return XSD File
     * @throws EditedRegionsException
     */
    public static File getXSDFile(String xsdFileName)
            throws EditedRegionsException {

        IPathManager pathMgr = PathManagerFactory.getPathManager();

        LocalizationContext commonStaticBase = pathMgr.getContext(
                LocalizationContext.LocalizationType.COMMON_STATIC,
                LocalizationContext.LocalizationLevel.BASE);

        String xsdPath = "";

        try {
            xsdPath = pathMgr.getFile(
                    commonStaticBase,
                    "ncep" + File.separator + EditedEventsConstants.PLUGIN_NAME
                            + File.separator + xsdFileName).getCanonicalPath();

        } catch (Exception e) {
            throw new EditedRegionsException(
                    "Error reading schema file with name '" + xsdFileName
                            + "'.");
        }

        File xsdFile = new File(xsdPath);
        return xsdFile;

    }

}
