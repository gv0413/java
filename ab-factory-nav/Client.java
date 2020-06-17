class Location {}
abstract class GPS {
  public abstract Location findCurrentLocation();
}

class CheapGPS extends GPS {
  public Location findCurrentLocation() {
    System.out.println("Find current location with cheap GPS");
    return null;
  }
}

class ExpensiveGPS extends GPS {
  public Location findCurrentLocation() {
    System.out.println("Find current location with Expensive GPS");
    return null;
  }
}
//----------------------------------
abstract class Map{}
class SmallMap extends Map{}
class LargeMap extends Map{}
abstract class Screen {
  public abstract void drawMap(Map map);
}
class SDScreen extends Screen {
  public void drawMap(Map map) {
    System.out.println("Draw map " + map.getClass().getName() + " on SD screen");
  }
}
class HDScreen extends Screen {
  public void drawMap(Map map) {
    System.out.println("Draw map " + map.getClass().getName() + " on HD screen");
  }
}
//---------------------------------
class Path{}
abstract class PathFinder {
  public abstract Path findPath(Location from, Location to);
}
class SlowPathFinder extends PathFinder {
  public Path findPath(Location from, Location to) {
    System.out.println("Slow Path Finder");
    return null;
  }
}
class FastPathFinder extends PathFinder {
  public Path findPath(Location from, Location to) {
    System.out.println("Fast Path Finder");
    return null;
  }
}
//----------------------------------------
public class Client {
  public static void main(String[] args) {
    NaviFactory factory = NaviFactoryFactory.getFactory(NaviType.BASIC);

    GPS gps = factory.createGPS();
    Screen mapScreen = factory.createScreen();
    PathFinder pathFinder = factory.createPathFinder();
    Map map = factory.createMap();

    mapScreen.drawMap(map);

    Location l1 = gps.findCurrentLocation();
    Location l2 = gps.findCurrentLocation();

    pathFinder.findPath(l1, l2);
  }
}
enum NaviType{BASIC, PREMIUM}
class NaviFactoryFactory {
  public static NaviFactory getFactory(NaviType type) {
    NaviFactory factory = null;
    switch(type) {
      case BASIC:
        factory = BasicNaviFactory.getInstance();
        break;
      case PREMIUM :
        factory = PremiumNaviFactory.getInstance();
        break;
    }

    return factory;
  }
}

//공통적인 것을 추상팩토리로 먼저 만들자. gps, screen, map, pathfinder
abstract class NaviFactory {
  public abstract GPS createGPS();
  public abstract Screen createScreen();
  public abstract PathFinder createPathFinder();
  public abstract Map createMap();
}

//은닉. 구체적인 
class BasicNaviFactory extends NaviFactory {
  //싱글턴
  private static NaviFactory factory;
  private BasicNaviFactory() {
  }
  public static NaviFactory getInstance() {
    if(factory == null) factory = new BasicNaviFactory();
    return factory;
  }
  //ㅎㅎ
  public GPS createGPS() {
    return new CheapGPS();
  }
  public Screen createScreen() {
    return new SDScreen(); 
  }
  public PathFinder createPathFinder() {
    return new SlowPathFinder(); 
  }
  public Map createMap() {
    return new SmallMap(); 
  }
}

class PremiumNaviFactory extends NaviFactory {
  private static NaviFactory factory;
  private PremiumNaviFactory() {
  }
  public static NaviFactory getInstance() {
    if(factory == null) factory = new PremiumNaviFactory();
    return factory;
  }
  public GPS createGPS() {
    return new ExpensiveGPS();
  }
  public Screen createScreen() {
    return new HDScreen(); 
  }
  public PathFinder createPathFinder() {
    return new FastPathFinder(); 
  }
  public Map createMap() {
    return new LargeMap(); 
  }
}