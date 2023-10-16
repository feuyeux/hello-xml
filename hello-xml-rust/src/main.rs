
mod reader;
mod writer;

fn main() {
    writer::write_xml("xml/my_config.xml");
    reader::read_xml("xml/my_config.xml");
}

