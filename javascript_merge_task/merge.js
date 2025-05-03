const a = [
    { id: 1, name: "A" },
    { id: 2, name: "B" },
    { id: 3, name: "C" },
    { id: 4, name: "D" },
  ];
  
  const b = [
    { id: 2, role: "X" },
    { id: 3, role: "Y" },
    { id: 4, role: "Z" },
    { id: 5, role: "M" },
  ];
  
  
  function mergeArraysById(a, b) {
    const mapA = new Map();
    a.forEach(obj => mapA.set(obj.id, obj));
  
    const mapB = new Map();
    b.forEach(obj => mapB.set(obj.id, obj));
  
    // Get all unique ids
    const allIds = new Set([...mapA.keys(), ...mapB.keys()]);
  
    // Collect all possible keys (excluding id)
    const allKeys = new Set();
    for (const obj of [...a, ...b]) {
      for (const key of Object.keys(obj)) {
        if (key !== 'id') {
          allKeys.add(key);
        }
      }
    }
  
    const merged = [];
  
    for (const id of allIds) {
      const objA = mapA.get(id) || {};
      const objB = mapB.get(id) || {};
      const combined = { id };
  
      for (const key of allKeys) {
        if (objA.hasOwnProperty(key)) {
          combined[key] = objA[key];
        } else if (objB.hasOwnProperty(key)) {
          combined[key] = objB[key];
        } else {
          combined[key] = null;
        }
      }
  
      merged.push(combined);
    }
  
    return merged;
  }
  
  
  
  const result = mergeArraysById(a, b);
  console.log(result);
  
  