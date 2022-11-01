import { useEffect, useState, useRef } from 'react';

function useEditor(initialValue) {
  const editorRef = useRef();
  const [value, setValue] = useState(initialValue);

  useEffect(() => {
    setValue(editorRef.current?.getInstance().getMarkdown());
  }, [value]);

  const reset = () => {
    setValue(initialValue);
  };

  const bind = {
    ref: editorRef,
    onChange: () => {
      setValue(editorRef.current?.getInstance().getMarkdown());
    },
  };

  return [value, bind, reset];
}

export default useEditor;
