import React, { useState } from 'react';

interface SmsPartsDisplayProps {
  parts: string[];
}

export const SmsPartsDisplay: React.FC<SmsPartsDisplayProps> = ({ parts }) => {
  const [copiedIndex, setCopiedIndex] = useState<number | null>(null);

  const copyToClipboard = async (text: string, index: number) => {
    try {
      await navigator.clipboard.writeText(text);
      setCopiedIndex(index);
      setTimeout(() => setCopiedIndex(null), 2000);
    } catch (err) {
      console.error('Failed to copy text: ', err);
    }
  };

  if (parts.length === 0) {
    return null;
  }

  return (
    <div className="w-full max-w-4xl mx-auto mt-8">
      <div className="text-center mb-6">
        <h3 className="text-2xl font-bold text-white mb-2">
          📨 SMS Parts ({parts.length})
        </h3>
        <p className="text-white/80">
          Your message has been split into {parts.length} part{parts.length !== 1 ? 's' : ''}
        </p>
      </div>

      <div className="grid gap-4 md:grid-cols-2 lg:grid-cols-3">
        {parts.map((part, index) => (
          <div
            key={index}
            className="bg-white/90 backdrop-blur-lg rounded-xl shadow-lg p-6 border border-white/30 hover:shadow-xl transition-all duration-300 transform hover:scale-105"
          >
            <div className="flex items-center justify-between mb-4">
              <div className="flex items-center space-x-2">
                <div className="w-8 h-8 bg-gradient-to-r from-blue-500 to-purple-600 text-white rounded-full flex items-center justify-center text-sm font-bold">
                  {index + 1}
                </div>
                <span className="text-sm font-medium text-gray-600">
                  Part {index + 1} of {parts.length}
                </span>
              </div>
              
              <button
                onClick={() => copyToClipboard(part, index)}
                className="p-2 text-gray-400 hover:text-gray-600 transition-colors duration-200 rounded-lg hover:bg-gray-100"
                title="Copy to clipboard"
              >
                {copiedIndex === index ? (
                  <div className="flex items-center space-x-1">
                    <svg className="w-4 h-4 text-green-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M5 13l4 4L19 7" />
                    </svg>
                    <span className="text-xs text-green-500">Copied!</span>
                  </div>
                ) : (
                  <svg className="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M8 16H6a2 2 0 01-2-2V6a2 2 0 012-2h8a2 2 0 012 2v2m-6 12h8a2 2 0 002-2v-8a2 2 0 00-2-2h-8a2 2 0 00-2 2v8a2 2 0 002 2z" />
                  </svg>
                )}
              </button>
            </div>

            <div className="bg-gray-50 rounded-lg p-4 border-l-4 border-blue-500">
              <p className="text-gray-800 text-sm leading-relaxed whitespace-pre-wrap">
                {part}
              </p>
            </div>

            <div className="mt-4 flex items-center justify-between text-xs text-gray-500">
              <span>{part.length} characters</span>
              <span>
                {part.length > 160 ? '📱 Unicode SMS' : '📞 Standard SMS'}
              </span>
            </div>
          </div>
        ))}
      </div>

      {/* Summary Card */}
      <div className="mt-6 bg-white/80 backdrop-blur-lg rounded-xl shadow-lg p-6 border border-white/30">
        <div className="text-center">
          <h4 className="text-lg font-semibold text-gray-800 mb-2">📊 Summary</h4>
          <div className="grid grid-cols-2 md:grid-cols-4 gap-4 text-sm">
            <div className="bg-blue-50 rounded-lg p-3">
              <div className="text-2xl font-bold text-blue-600">{parts.length}</div>
              <div className="text-gray-600">Total Parts</div>
            </div>
            <div className="bg-green-50 rounded-lg p-3">
              <div className="text-2xl font-bold text-green-600">
                {parts.reduce((total, part) => total + part.length, 0)}
              </div>
              <div className="text-gray-600">Total Characters</div>
            </div>
            <div className="bg-purple-50 rounded-lg p-3">
              <div className="text-2xl font-bold text-purple-600">
                {Math.min(...parts.map(part => part.length))}
              </div>
              <div className="text-gray-600">Shortest Part</div>
            </div>
            <div className="bg-orange-50 rounded-lg p-3">
              <div className="text-2xl font-bold text-orange-600">
                {Math.max(...parts.map(part => part.length))}
              </div>
              <div className="text-gray-600">Longest Part</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}; 