import { useEffect, useState, useRef } from 'react';

function useEditor(initialValue, initialError, name) {
  const editorRef = useRef();
  const [value, setValue] = useState(initialValue);
  const [error, setError] = useState(initialError);

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
      if (editorRef.current?.getInstance().getMarkdown() === '') {
        setError(`${name} is Missing`);
      } else if (editorRef.current?.getInstance().getMarkdown().length < 20) {
        setError(`${name} must be at least 20 characters.`);
      } else {
        setError(initialError);
      }
    },
  };

  return [value, bind, reset, error];
}

export default useEditor;
