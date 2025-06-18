package com.jphilips.library.bookinventory.service.command;

import com.jphilips.library.bookinventory.dto.cqrs.DeleteBookInventoryCommand;
import com.jphilips.library.bookinventory.service.helper.BookInventoryManager;
import com.jphilips.shared.util.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteBookInventoryCommandService implements Command<DeleteBookInventoryCommand, Void> {

    private final BookInventoryManager bookInventoryManager;

    @Override
    public Void execute(DeleteBookInventoryCommand command) {

        var inventory = bookInventoryManager.validateById(command.id());

        bookInventoryManager.delete(inventory);

        return null;
    }
}
