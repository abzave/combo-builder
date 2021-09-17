package patternInterfaces;

public interface IPrototype<T extends IPrototype> {
    public T clone();
    public T deepClone();
}