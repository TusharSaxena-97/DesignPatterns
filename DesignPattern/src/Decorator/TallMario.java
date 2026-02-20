package Decorator;

public class TallMario extends ICharacterDecorator{

    TallMario(ICharacter character )
    {
        this.character = character;
    }

    @Override
    public String getAbilities() {
        return character.getAbilities() + " + Tall";
    }
}
