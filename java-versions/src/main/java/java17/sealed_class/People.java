package java17.sealed_class;

public sealed interface People {

    final class Coders implements People {
    }

    final class MoreCoders implements People {
    }
}
