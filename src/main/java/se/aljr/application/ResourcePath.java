package se.aljr.application;

public class ResourcePath {
    public static String getResourcePath(){
        return ResourcePath.class.getClassLoader().getResource("resource.path").getPath().replace("resource.path","");
    }
}
