
import org.xml.sax.*;
import org.xml.sax.helpers.*;

class SAXHandler extends DefaultHandler 
{
	public int ileTab=0;
	public int ileNL=0;
	public int razem=0;
	public int wszystkieznaki=0;
  public void startDocument() {
    System.out.println("Zliczamy znaki..");
  }

  public void endDocument() {
  	 razem=ileTab+ileNL+wszystkieznaki;
  	 System.out.println("Koniec.");
    System.out.println("Tabulacji jest "+ileTab+" znakow nowej linii jest "+ileNL);
    System.out.println("Ilosc znakow elementow pliku "+wszystkieznaki);
    System.out.println("Wszystkie znaki jakie wystepuja "+razem);
    
  }

  public void startElement(String namespaceURL, String localName,
                           String qName, Attributes atts) {
      }

  public void endElement(String namespaceURL, String localName,
                         String qName) {
  }  

  public void characters(char ch[], int start, int length) {

		for(int i = start; i < start + length; i++) {
      switch(ch[i]) {
        case '\n':
           ileNL++;
          break;
        case '\t':
           ileTab++;
          break;
        default:
           wszystkieznaki++;
          break;
      }
    }
 
    System.out.println();
  }

} 

public class Main {
  public static void main(String[] args) throws Exception {
    String uri;

    if(args.length == 0) {
      throw new Exception("Podaj nazwe pliku");
    } else {
      uri = args[0];
    }

    XMLReader parser = XMLReaderFactory.createXMLReader();
    SAXHandler handler = new SAXHandler();
    parser.setContentHandler(handler);
    parser.parse(uri);
  }
}