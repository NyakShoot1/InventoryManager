import io
from typing import List

from docxtpl import DocxTemplate

from src.items.schemas import Item
from src.positions.schemas import PositionCreate


def generate_prihod_doc(
    document_number,
    supplier_name,
    positions: List[PositionCreate],
    delivery_man,
    storage_man,
):
    template = DocxTemplate("../templates/prihod.docx")
    items = []
    items_count = 0
    price = 0
    for position in positions:
        items_count += position.count
        price += items[position.item_id].purchase_price * position.count

    context = {
        "document_number": document_number,
        "date": 1,
        "supplier_name": supplier_name,
        "items": items,
        "positions": positions,
        "items_count": items_count,
        "price": price,
        "delivery_man": delivery_man,
        "storage_man": storage_man,
    }

    file_stream = io.BytesIO()
    template.render(context)
    template.save(file_stream)

    return file_stream


# generate_prihod_word()
