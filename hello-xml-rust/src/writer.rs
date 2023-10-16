use std::fs::{File, OpenOptions};
use xml::EventWriter;
use xml::writer::XmlEvent;

pub(crate) fn write_xml(path: &str) {
    let file = OpenOptions::new()
        .read(true)
        .write(true)
        .create(true)
        .open(path).unwrap();
    let mut w = EventWriter::new(file);
    let e1: XmlEvent = XmlEvent::start_element("config").into();
    if let Err(e) = w.write(e1) {
        panic!("Write error: {e}")
    }
    write_node(&mut w, "mode", "1");
    write_node(&mut w, "unit", "901");
    write_node(&mut w, "current", "0");
    write_node(&mut w, "interactive", "0");
    let e2: XmlEvent = XmlEvent::end_element().into();
    if let Err(e) = w.write(e2) {
        panic!("Write error: {e}")
    }
}

fn write_node(w: &mut EventWriter<File>, k: &str, v: &str) {
    if let Err(e) = w.write(XmlEvent::Characters("\t")) {
        panic!("Write error: {e}")
    }
    if let Err(e) = w.write(XmlEvent::start_element(k)) {
        panic!("Write error: {e}")
    }
    if let Err(e) = w.write(XmlEvent::Characters(v)) {
        panic!("Write error: {e}")
    }
    if let Err(e) = w.write(XmlEvent::end_element()) {
        panic!("Write error: {e}")
    }
    if let Err(e) = w.write(XmlEvent::Characters("\n")) {
        panic!("Write error: {e}")
    }
}