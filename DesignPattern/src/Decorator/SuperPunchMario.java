package Decorator;

public class SuperPunchMario extends ICharacterDecorator {

    SuperPunchMario(ICharacter character)
    {
        this.character = character;
    }

    @Override
    public String getAbilities() {
        return character.getAbilities() + " + Super Punch";
    }
}
