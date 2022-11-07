import { Modal, Button } from 'react-bootstrap';

function Popup({ open, setPopup, message, title, callback }) {
  const handleClose = () => {
    setPopup({ open: false });
    if (callback) {
      callback();
    }
  };

  return (
    <>
      <Modal show={open} onHide={handleClose} style={{ marginTop: '50px' }}>
        <Modal.Header closeButton>
          <Modal.Title>{title}</Modal.Title>
        </Modal.Header>
        <Modal.Body>{message}</Modal.Body>
        <Modal.Footer>
          <Button variant="primary" onClick={handleClose}>
            OK
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default Popup;
