package full.features.java_next.lang.sealed;

public sealed interface People {

    final class Coders implements People {
    }

    final class MoreCoders implements People {
    }
}
